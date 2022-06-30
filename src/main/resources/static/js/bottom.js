
// BEGIN Global document ready events/functions/variables
// -------
$(function() {

    // BEGIN Triggers declarations
    // -------

    initBlockUi();
    initFormSubmission();
    initFormRecaptchaSubimission();
    initFormValidation();
    initSelect2ComboBox();
    initDatepicker();
    initTimepicker();
    initCheckboxYesNo();
    initMask();
    initI18nTelInput();
    if(! window.JoditManualInit){
        initJoditEditor();
    }

    fillTooltips();
    initPortletsActions();

    // -------
    // END Triggers declarations

});
// -------
// END Global document ready events/functions/variables




//BEGIN Global Functions
//-------

function block(message) {

    var defaultMessage =
        `<h3 style='color: #d2d2d2;'>
            <i class='fa fa-spinner fa-spin'></i>&nbsp; ${i18n.block}
         </h3>`;
    unblock()
    $.blockUI({
        message : message ? message : defaultMessage,
        overlayCSS: {
            backgroundColor: '#000000',
            opacity: 0.8,
            cursor: 'wait'
        },
        css : {
            border : 'none',
            padding : '15px',
            opacity : .9,
            backgroundColor : '#000000',
            color : '#ffffff !important',
            'border-radius' : '7px',
            cursor: 'wait'
        },
        baseZ: 2000
    });
}
function unblock(){
    $.unblockUI();
}

function getParsleyLanguage(){
    var lang = 'en'
    if(SYSTEM_LOCALE != 'en-US'){
        var especialLanguages = ['pt', 'zh']
        if(especialLanguages.includes(SYSTEM_LANGUAGE)){
            lang = SYSTEM_LOCALE.toLowerCase();
        } else {
            lang = SYSTEM_LANGUAGE
        }
    }

    return lang;
}

function initBlockUi() {

    $('a').click(function(e) {

        var href = $(this).attr('href');
        var target = $(this).attr('target');
        if (href !== undefined
            && href.startsWith('#') == false
            && href !== ''
            && href !== '#'
            && href !== 'javascript:void(0)'
            && href !== 'void(0)'
            && target !== 'blank'
            && target !== '_blank') {
            block();
        }
    });
}

function initFormSubmission() {

    $('form').submit(function(event) {

        if ($('.mask-money')[0]) {
            $('.mask-money').each(function(){
                this.value = $(this).maskMoney('unmasked')[0];
            });
        }

        if($(event.target).parsley() !== undefined && $(event.target).parsley().isValid() == false){
            event.preventDefault()
            return;
        }
        block();
    });
}

function initFormRecaptchaSubimission() {

     if ($('#btnRecaptchaSubmit')[0]) {
         $('#btnRecaptchaSubmit').on('click', function(e) {
             e.preventDefault();
             $('#editForm').parsley().validate()
             if ($('#editForm').parsley().isValid()) {
                 $('#btnRealSubmit').trigger('click');
             }
         });
     }
}

function validatePhone(value, el){

    var brDialCode = 55;
    var dialCodeLength = $('#dialCode').val().length + 1;
    var isBrazil = $('#dialCode').val() == brDialCode;
    var maskLength = el.attr('placeholder').length;
    var maskAndDialCodeLength = maskLength + dialCodeLength;
    if ($.trim(value).startsWith('+') && $.trim(value).length !== maskAndDialCodeLength ) {
        return false;
    } else if(isBrazil && $.trim(value).startsWith('+')
        && $.trim(value).length !== maskAndDialCodeLength
        && $.trim(value).length !== (maskAndDialCodeLength - 1)) {
        return false;
    } else if (isBrazil && !$.trim(value).startsWith('+')
        && $.trim(value).length !== maskLength
        && $.trim(value).length !== (maskLength - 1)) {
        return false;
    } else if (!isBrazil && !$.trim(value).startsWith('+') && $.trim(value).length !== maskLength) {
        return false;
    }
    return true;
}

function initFormValidation() {

    if ($('#editForm')[0]) {

        var form = $('#editForm').parsley({
            errorsContainer: function(el) {
                return el.$element.closest('.form-group');
            }
        });
    }

    window.Parsley.addValidator('phonelength', {
        validateString: function(value, args, el) {
            return validatePhone(value, el.$element);
        }
    });

    window.Parsley.addValidator('phonelengthwithtwofactorauth', {
        validateString: function(value, elId, el) {

            isValid = validatePhone(value, el.$element);
            handleTwoFactorAuth(isValid, elId);
            return isValid;
        }
    });

    window.Parsley.addValidator('requiredifnotempty', {
        validateString: function(value, compareTo) {
            if ($.trim(value) === '' && $.trim($(compareTo).val()) !== '') {
                return false;
            }
            return true;
        }
    });

    window.Parsley.addValidator('daterange', {
        validateString: function(value, options, elParsley) {
            var compareTo;
            var dateRangeOptions = {
                compareToId: null,
                rangeInDays: 0
            }
            dateRangeOptions = (typeof options == 'string') ? JSON.parse(options.replace(/'/gm, '"')) : options;

            if( !$(dateRangeOptions.compareToId)[0] ) return true;
            var otherDateInput = $(dateRangeOptions.compareToId)

            value  = moment(value, DateUtil.dateFormat.moment);
            compareTo = moment(otherDateInput.val(), DateUtil.dateFormat.moment);

            if(Math.abs(value.diff(compareTo, 'days')) > dateRangeOptions.rangeInDays){
                var lang = getParsleyLanguage();
                window.Parsley.addMessage(lang, 'daterange', i18n.dateBetween.replace('{0}', dateRangeOptions.rangeInDays));
                return false
            }

            return true;
        }
    });

    window.Parsley.addValidator('daterangeyears', {
        validateString: function(value, options, elParsley) {
            var compareTo;
            var dateRangeOptions = {
                compareToId: null,
                rangeInYears: 0
            }
            dateRangeOptions = (typeof options == 'string') ? JSON.parse(options.replace(/'/gm, '"')) : options;

            if( !$(dateRangeOptions.compareToId)[0] ) return true;
            var otherDateInput = $(dateRangeOptions.compareToId)

            value  = moment(value, DateUtil.dateFormat.moment);
            compareTo = moment(otherDateInput.val(), DateUtil.dateFormat.moment);

            if(Math.abs(value.diff(compareTo, 'years')) > dateRangeOptions.rangeInYears){
                var lang = getParsleyLanguage();
                window.Parsley.addMessage(lang, 'daterangeyears', i18n.dateBetweenYears.replace('{0}', dateRangeOptions.rangeInYears));
                return false
            }

            return true;
        }
    });

    window.Parsley.addValidator('dateformat', {
        validateString: function(value, format, elParsley) {
            value  = moment(value, format);
            if( !value.isValid() ){
                return false
            }
            return true;
        }
    });

    window.Parsley.addValidator('datelessthan', {
        validateString: function(startValue, endId) {

            var endValue = $(endId).val();
            if (startValue === '' || startValue === undefined || endValue === '' || endValue === undefined) {
                return true;
            }

            var { startDate, endDate } = formatStartEndDate(startValue, endValue);

            if (startDate.isBefore(endDate, 'minute')) {
                return true;
            }
            return false;
        },
        messages: {
            [getParsleyLanguage()]: i18n.dateLessThan
        }
    });

    window.Parsley.addValidator('datelessequalthan', {
        validateString: function(startValue, endId) {

            var endValue = $(endId).val();
            if (startValue === '' || startValue === undefined || endValue === '' || endValue === undefined) {
                return true;
            }

            var { startDate, endDate } = formatStartEndDate(startValue, endValue);

            if (startDate.isSameOrBefore(endDate, 'minute')) {
                return true;
            }
            return false;
        },
        messages: {
            [getParsleyLanguage()]: i18n.dateLessEqualThan
        }
    });

    window.Parsley.addValidator('dategreaterthantoday', {
        validateString: function(startValue) {
        	var endValue = moment(new Date()).format(DateUtil.dateFormat.moment);

            if (startValue === '' || startValue === undefined || endValue === '' || endValue === undefined) {
                return true;
            }

            var { startDate, endDate } = formatStartEndDate(startValue, endValue);

            if (startDate.isSameOrAfter(endDate, 'minute')) {
                return true;
            }
            return false;
        },
        messages: {
            [getParsleyLanguage()]: i18n.dateGreaterThanToday
        }
    });

    window.Parsley.addValidator('dategreaterthan', {
        validateString: function(endValue, startId) {

        	var startValue = $(startId).val();
            if (endValue === '' || endValue === undefined || startValue === '' || startValue === undefined) {
                return true;
            }

            var { endDate, startDate } = formatStartEndDate(startValue, endValue);
            $(startId).parsley().validate()
            return true;
//            if (endDate.isAfter(startDate, 'minute')) {
//                return true;
//            }
//            return false;
//        },
//        messages: {
//            [getParsleyLanguage()]: i18n.dateGreaterThan
        }
    });

    function currentFormattedDate(){
        var data = new Date(),
            day  = data.getDate().toString().padStart(2, '0'),
            month  = (data.getMonth()+1).toString().padStart(2, '0'),
            year  = data.getFullYear();
        return day+"/"+month+"/"+year;
    }

}

function formatStartEndDate(startValue, endValue) {

    var startDate, endDate;
    var startTime = '#startTime';
    var endTime = '#endTime';
    
    if ($(startTime).val() !== undefined
        && $(endTime).val() !== undefined
        && $(startTime).val() !== ''
        && $(endTime).val() !== '') {

          if($(startTime).val().includes('AM')
            || $(startTime).val().includes('PM')
            || $(endTime).val().includes('AM')
            || $(endTime).val().includes('PM')){
            startDate = moment(startValue + ' ' + $(startTime).val(), DateUtil.timestampFormat_HH_MM_A.moment);
            endDate = moment(endValue + ' ' + $(endTime).val(), DateUtil.timestampFormat_HH_MM_A.moment);
        }else{
            startDate = moment(startValue + ' ' + $(startTime).val(), DateUtil.timestampFormat_HH_MM.moment);
            endDate = moment(endValue + ' ' + $(endTime).val(), DateUtil.timestampFormat_HH_MM.moment);
        }
    }
    else {
        startDate = moment(startValue, DateUtil.dateFormat.moment);
        endDate = moment(endValue, DateUtil.dateFormat.moment);
    }
    return { startDate, endDate };
}

function handleTwoFactorAuth(isValid, elId) {

    var elPhone = $(elId);
    var elTwoFactorAuth = $('#twoFactorAuth');
    if (isValid) {
        // elPhone.parent().parent().parent().parent().removeClass('col-md-12');
        // elPhone.parent().parent().parent().parent().addClass('col-md-6');
        elTwoFactorAuth.parent().parent().parent().show();
    } else {
        // elPhone.parent().parent().parent().parent().removeClass('col-md-6');
        // elPhone.parent().parent().parent().parent().addClass('col-md-12');
        elTwoFactorAuth.parent().parent().parent().hide();
        if (elTwoFactorAuth[0] !== undefined) {
            elTwoFactorAuth[0].checked = false;
            elTwoFactorAuth.trigger('change');
        }
    }
}


function initSelect2ComboBox() {

    if ($('select.select2')[0]) {
        $('select.select2').select2();
    }
}

function initDatepicker() {

    if ($('.datepicker')[0]) {
        $('.datepicker').datepicker({
            autoclose: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy',
            language: 'pt-BR',
            clearBtn: false
        });
    }
}

function initCheckboxYesNo() {

    $('.checkbox-yes-no').change(function() {
        $(this).parent()
        .find('label')
        .html(this.checked ? i18n.yes : i18n.no);
    });
}

function initTimepicker() {
    if ($('.timepicker')[0]) {
        $('.timepicker').timepicker({
            timeFormat: SYSTEM_LOCALE == 'en-US' ? 'hh:mm a' : 'HH:mm',
            showMeridian: SYSTEM_LOCALE == 'en-US',
            defaultTime: false,
            icons: {
                up: 'mdi mdi-chevron-up',
                down: 'mdi mdi-chevron-down'
            }
        }).on("change", function(e) {
        	$(this).parsley().validate()
        });
    }
}

function initMask() {

    if ($('.mask-numeric')[0]) {
        $('.mask-numeric').mask('0#');
    }

    if ($('.mask-percentage')[0]) {
        $('.mask-percentage').mask('##0.00', { reverse: true });
    }

    if ($('.mask-money')[0]) {
        $('.mask-money').attr('placeholder', '0.00')
        $('.mask-money').maskMoney({
            prefix: '',
            decimal: '.',
            thousands: ',',
            allowNegative: false
        });
    }

    if ($('.mask-date')[0]) {
        $('.mask-date').mask('00/00/0000', { placeholder: '__/__/____' });
    }

    if ($('.mask-time')[0]) {
    	$(".mask-time").each(function() {
    		var value = $(this).val();
    		if (value != '' && value !== undefined) {
    			$(this).val(leftPad(value, SYSTEM_LANGUAGE != 'en' ? 5 : 8))
    		}
        })
        SYSTEM_LANGUAGE != 'en' ?
        $('.mask-time').mask('00:00' , { placeholder: '__:__' }) :
        $('.mask-time').mask('00:00 ZM' , {
            translation: {
              'Z': {
                pattern: /[AP]/
              }
            },
            placeholder: "__:__ AM"
          } );
    }
}

function leftPad(str, max) {
    str = str.toString();
    return str.length < max ? leftPad("0" + str, max) : str;
}

function initPortletsActions() {
    if($('.collapse')[0] && $('.portlet-widgets [data-toggle="collapse"] i')[0] ){
        var collapsePanel = $('.collapse');
        var openCloseIcon = $('.portlet-widgets [data-toggle="collapse"] i');
        collapsePanel.on('show.bs.collapse', function(event){
            var openCloseInstance = $(event.target).parent().find('.portlet-widgets [data-toggle="collapse"] i');
            openCloseInstance.attr('class', '')
            openCloseInstance.addClass('mdi mdi-minus')
        })
        collapsePanel.on('hide.bs.collapse', function(event){
            var openCloseInstance = $(event.target).parent().find('.portlet-widgets [data-toggle="collapse"] i');
            openCloseInstance.attr('class', '')
            openCloseInstance.addClass('mdi mdi-plus')
        })
    }

}



function buildDataTable(columnDefinitions) {

    return {
        columnDefs : columnDefinitions,
        language: createLanguageDataTable(),
        drawCallback: dataTableDrawCallbackFunction,
        autoWidth: false,
        responsive: {
            details: false
        },
        dom:
            `<'row'
                <'col-sm-12 col-md-6 pr-0'f>
                <'col-sm-12 col-md-6'l>
            >
            <'row'
                <'col-sm-12't>
            >
            <'row'
                <'col-sm-12 col-md-4'i>
                <'col-sm-12 col-md-8'p>
            >`,
    };
}
function buildListItemsDataTable(data, columns) {
    return {
		data,
		columns,
		destroy: true,
        language: createLanguageDataTable(),
        drawCallback: dataTableDrawCallbackFunction,
        autoWidth: false,
        responsive: {
            details: false
        },
        dom:
            `<'row'
                <'col-sm-12 col-md-6 pr-0'f>
                <'col-sm-12 col-md-6'l>
            >
            <'row'
                <'col-sm-12't>
            >
            <'row'
                <'col-sm-12 col-md-4'i>
                <'col-sm-12 col-md-8'p>
            >`,
    };
}


function createLanguageDataTable() {

    if(SYSTEM_LANGUAGE != 'en') {

        var especialDataTablesCountriesLocales = {
            'pt-br': 'Brazil',
            no: 'Bokmal',
            nb: 'Bokmal',
            nn: 'Nynorsk'
        }
        var dataTablesi18nUrl = `${ASSETS_BASE_URL}/plugins/datatables/i18n/`
        var languageFile;

        if(especialDataTablesCountriesLocales[SYSTEM_LOCALE.toLowerCase()] != undefined){
            languageFile = `${DATATABLES_LANGUAGE}-${especialDataTablesCountriesLocales[SYSTEM_LOCALE.toLowerCase()]}`
        } else{
            languageFile =  DATATABLES_LANGUAGE;
        }
        return {
            url: `${dataTablesi18nUrl}${languageFile}.json`
        }
    } else {
        return {
            sSearch: '',
            searchPlaceholder: i18n.dtSearch + '...',
            info: i18n.dtInfo,
            zeroRecords: i18n.dtZeroRecords,
            infoFiltered: i18n.dtinfoFiltered,
            paginate: {
                next: '>',
                previous: '<'
            },
            lengthMenu: i18n.dtlengthMenu
        }
    }
}

function initI18nTelInput() {

    if ($('.phone')[0]) {

        var input = document.querySelector('.phone');
        iti = window.intlTelInput(input, {
            autoPlaceholder: 'aggressive',
            preferredCountries: ['us', 'ca', 'br'],
            utilsScript: utilsI18nPhoneInput
        });

        iti.promise.then(function() {
            handleI18nTelInput(input, iti);
        });

        input.addEventListener('countrychange', function(e) {

            var elPhone = $('.phone');
            if ($('#twoFactorAuth')[0]) {
                handleTwoFactorAuth(false, elPhone);
            }
            elPhone.val('');
            handleI18nTelInput(input, iti);
        });
    }
}
var DEFAULT_MAX_LENGTH = 30;
function handleI18nTelInput(inputEl, iti) {
    var countryMask;

    $('#dialCode').val(iti.getSelectedCountryData().dialCode);
    if (iti.getSelectedCountryData().iso2 === 'br') {
        var brazilMaskBehavior = function (val) {
            return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
            },
            phoneNumberOptions = {
                onKeyPress: function(val, e, field, options) {
                    field.mask(brazilMaskBehavior.apply({}, arguments), options);
                }
            }
        ;
        $('.phone').mask(brazilMaskBehavior, phoneNumberOptions);
    } else {
        countryMask = $('.phone').attr('placeholder').replace(/[0-9]/g, 0);
        $('.phone').mask(countryMask);
    }
    inputEl.maxLength = DEFAULT_MAX_LENGTH;
}

function dataTableDrawCallbackFunction(settings) {

    var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
    pagination.css('visibility', 'hidden');
    if (this.api().page.info().pages > 1) {
        pagination.css('visibility', 'visible');
    }

    $('.delete').click(function() {
        $('#deleteId').val($(this).data('id'));
        $('#deleteForm').attr('action', $(this).data('url'));
    });

    initBlockUi();
    fillTooltips();
    initShowHidePin();
    initSwitchery();
    initCheckAssociations();
    initPortletsActions();
}

var clickedSwitcheryEl;
function initSwitchery() {

    if ($('.switchery')[0]) {
        $('.switchery').unbind('change');
        $('.switchery').change(function() {
            var id = $(this).data('id');
            var activate = $(this).prop('checked');
            clickedSwitcheryEl = $(this);
            var url = urlActivateSwitchery + '/' + id + '/' + activate;
            call(GET, url, handleSwitcheryResponse);
        });
    }
}

function handleSwitcheryResponse(response) {

    if (!response || (response.success !== undefined && !response.success)) {
        $('.switchery').unbind('change');
        clickedSwitcheryEl.trigger('click');
        initSwitchery();

        showAlertError(response.message);
    }

    if((response || response.success) && clickedSwitcheryEl.data('sync')) {
        var $prevTD = clickedSwitcheryEl.parent().prev().children();

        $prevTD.removeClass();
        $prevTD.addClass('ion ion-md-close-circle text-danger icn-bool tooltip-top');
    }
}

function initShowHidePin() {

	if ($('.show-hide-pin')[0]) {

		$(".show-hide-pin").unbind('click');

        $('.show-hide-pin').click(function(event) {

            var el = $(event.target).parent();
            var pinEl = el.parent().parent().find('.pin');
            $(this).toggleClass("fa-eye fa-eye-slash")
            if(pinEl.text().startsWith('*')) {
                pinEl.text(el.data().pin);
                el.tooltipster('content', i18n.hide);

            } else {
                var pin = pinEl.text();
                var ast =  pin.replace(/[0-9#]/g, '*');
                pinEl.text(ast);
                el.tooltipster('content', i18n.show);
            }
        })
	}
}
var modalDeleteWarning;
function initCheckAssociations(){

    if($('.checkAssociations')[0]){

    	$(".checkAssociations").unbind('click');

        $('.checkAssociations').click(function() {
            var id = $(this).children("a").data("id");
            var url = urlCheckAssociations + "/" + id;
            modalDeleteWarning = $($(this).attr("data-target"))
            call(GET, url, handleCheckAssociationsResponse);
        });
    }
}

function handleCheckAssociationsResponse(response) {
    if(response){
    	modalDeleteWarning.find('#modalDeleteDesc').text(i18n.associationDescription);
    }
}

function fillTooltips() {

    if ($('.tooltip-left')[0]) {
        $('.tooltip-left:not(".tooltipstered")').tooltipster({
            theme: 'tooltipster-shadow', // [default | light | borderless | noir | punk | shadow]
            animation: 'grow', // [fade | grow | swing | slide | fall]
            delay: 200,
            touchDevices: true,
            trigger: 'hover',
            arrow: true,
            side: 'left'
        });
    }

    if ($('.tooltip-top')[0]) {
        $('.tooltip-top:not(".tooltipstered")').tooltipster({
            theme: 'tooltipster-shadow', // [default | light | borderless | noir | punk | shadow]
            animation: 'grow', // [fade | grow | swing | slide | fall]
            delay: 200,
            touchDevices: true,
            trigger: 'hover',
            arrow: true,
            side: 'top'
        });
    }
}

function initJoditEditor() {

    if ($('.wysiwyg')[0]) {

        var editor = new Jodit('.wysiwyg', joditDefaultConfig);

        editor.events.on('change', () => {
            var editorInnerText = editor.editor.innerText;
            if(editorInnerText.length == 0 || editorInnerText.replace(/\n/g, '').length == 0){
                editor.setEditorValue('')
            }
        });
        editor.events.on('blur', () => {
            $('textarea.wysiwyg').parsley().validate();
        });

        return editor;
    }
}

function bindMultiSelect(elementId, selectableFooter, selectionFooter) {

    $('#' + elementId).multiSelect({
        selectableHeader: "<input type='text' class='form-control custom-mult-search-input' autocomplete='off' placeholder='" + i18n.filter + "'>",
        selectionHeader: "<input type='text' class='form-control custom-mult-search-input' autocomplete='off' placeholder='" + i18n.filter + "'>",
        selectableFooter: "<div class='custom-mult-select-footer'>" + selectableFooter + "</div>",
        selectionFooter: "<div class='custom-mult-select-footer'>" + selectionFooter + "</div>",
        selectableOptgroup: false,
        afterInit: function (ms) {
            var that = this,
                $selectableSearch = that.$selectableUl.prev(),
                $selectionSearch = that.$selectionUl.prev(),
                selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

            that.qs1 = $selectableSearch.quicksearch(selectableSearchString).on('keydown', function (e) {
                if (e.which === 40) {
                    that.$selectableUl.focus();
                    return false;
                }
            });
            that.qs2 = $selectionSearch.quicksearch(selectionSearchString).on('keydown', function (e) {
                if (e.which == 40) {
                    that.$selectionUl.focus();
                    return false;
                }
            });
        },
        afterSelect : function() {
            this.qs1.cache();
            this.qs2.cache();

            // This fix an API bug when you deselect and reselect an element
            this.$element.find('option').each(function() {
                if (this.outerHTML.indexOf('selected') >= 0) {
                    this.selected = true;
                }
            });
        },
        afterDeselect : function() {
            this.qs1.cache();
            this.qs2.cache();
        }
    });
}

function getRandomPin(length) {
    return Math.floor(Math.random() * (9 * Math.pow(10, length - 1))) + Math.pow(10, length - 1);
}

function inputImagePreview(input, elementId) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#' + elementId).parent()
                .find('.custom-file label')
                .html(input.files[0].name);
            $('#' + elementId).attr('src', e.target.result);
            $('#' + elementId).fadeIn();
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function refreshInputFileName(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('.custom-file label').html(input.files[0].name);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

var GET = 'GET';
var POST = 'POST';

function call(httpMethod, url, callBackFunction, formData) {

    $.ajax({
        type: httpMethod,
        url: url,
        cache: false,
        async: true,
        crossDomain: true,
        processData: false,
        contentType: false,
        data: formData,
        xhrFields: {
            withCredentials: true
        },
        success: callBackFunction,
        error: showAjaxError
    });
}

function showAjaxError(xhr, ajaxOptions, thrownError) {

    if (xhr && xhr.responseText) {
        console.log(xhr.responseText);
    }
    showAlertError(i18n.msgErrorOperation);
}

function showAlertError(message, persiste) {

    showAlert(message, type = 'error')
}

function showAlert(message, type) {

    if (!message && type == 'error') {
        message = i18n.msgErrorOperation;
    }
    $.Notification.autoHideNotify(
        type,
        'top center',
        capitalize(type),
        message
    );
}

function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
