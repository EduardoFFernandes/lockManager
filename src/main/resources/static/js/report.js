function initReportTriggers() {

    var startDateEl = $('#startDate');
    var endDateEl = $('#endDate');

    startDateEl.change(function() {

        startDateEl.parsley().validate();
        endDateEl.parsley().validate();

        if (startDateEl.parsley().isValid() && endDateEl.parsley().isValid()) {
            $('#editForm').submit();
        }
    });

    endDateEl.change(function() {

        startDateEl.parsley().validate();
        endDateEl.parsley().validate();

        if (startDateEl.parsley().isValid() && endDateEl.parsley().isValid()) {
            $('#editForm').submit();
        }
    });

    $('input[type="checkbox"]').change(function() {
        $('input[type="checkbox"]').not(this).prop('checked', false);
        $('#editForm').submit();
    });

    if ($('.select2')[0]) {
        $('.select2').change(function() {
            $('#editForm').submit();
        });
    }
}

function bindReportDataTable(sort, target, reportName, entityName) {

    var target = target === null ? 0 : target;
    var columnsDefinitions = [
        { targets : 'all' },
    ];
    var fileName = getReportExportFileName(reportName, entityName);
    var datatableConfig = buildReportDataTable(columnsDefinitions, fileName);
    var orderArray = [];


    if (sort != null) {
        orderArray.push([ target, sort ])
        datatableConfig.order = orderArray;
    }

    $('#datatable').DataTable(
        datatableConfig
    );
}

function getReportExportFileName(reportName, entityName) {

    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();

    startDate = startDate.replace(/\//g, '-')
    endDate = endDate.replace(/\//g, '-')

    var fileName = reportName + ' (' + startDate + ' __ ' + endDate + ')';;
    if (entityName) {
        fileName = reportName + ' ' + entityName + ' (' + startDate + ' __ ' + endDate + ')';
    }
    return fileName;
}
