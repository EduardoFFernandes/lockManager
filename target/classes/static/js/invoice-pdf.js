var Invoice = (function(){
    MONTHLY = "MONTHLY";

    var systemName = i18n.systemName;
    var accountSubscription = null;
    var invoice = null;

    function clearData(){
        accountSubscription = null;
        servicePlan = null;
        invoice = null;
    }

    function download(invoiceId){
        clearData();

        api.get(`/system/user/subscription/invoice/${invoiceId}`)
        .then(function(result){
            invoice = result.data;
            userData = invoice.userData;
            accountSubscription = invoice.accountSubscription;

            var docDefinition = createDocDefinition();
            var fileName = getFilename();
            pdfMake.createPdf(docDefinition).download(fileName);
        })
        .catch(function(error){
            console.log(error)
        })
    }

    function getFilename(){
        var dateString = formatDate(Date.now())
        dateString = dateString.replace(/\//g, "-")
        var systemNameToFileName = systemName.replace(/ /g, "_")
        return systemNameToFileName + "_" +i18n.invoice + "_" + dateString;
    }

    function getServicePlanPrice(){
        return accountSubscription.servicePlan.monthlyPrice;
    }

    function getPriceCurrencyFormatted(price){
        var localePrice = price.toLocaleString(SYSTEM_LOCALE, { style: 'currency', currency: invoice.currencyCode }); 
        return localePrice;
    }

    function formatDate(date){
        return moment(date).format(DateUtil.dateFormat.moment);
    }

    function generateMultiplyLockString(){
        var multiplyLockString = `${invoice.totalActiveLocks} X ${capitalize(i18n.locks)}`;
        return multiplyLockString;
    }

    function createProRataTable(){
        var data = Array(4).fill('');
        if(invoice.prorataValue != null){
            data = [];
            data.push(invoice.servicePlanNameOld);
            data.push(i18n.proRataDiscount);
            data.push(i18n.month)
            data.push('-' + (getServicePlanPrice() < invoice.prorataValue ? getPriceCurrencyFormatted(getServicePlanPrice()) : getPriceCurrencyFormatted(invoice.prorataValue)));
        }

        return data;
    }

    function createDocDefinition (){
        var docDefinition = {
            content: [
                {
                    text: systemName,
                    style: 'header',
                    margin: [0, 0],
                },
                {
                    text: invoice.invoiceNumber,
                    style: 'subheader',
                    margin: [0, 5, 0, 25],
                },
                {
                    text: [ {text: i18n.company, style: 'tableHeader' },  " : "  , invoice.accountName],
                    style: 'floatLeft'
                },
                {
                    text: [ {text: i18n.legalResponsible, style: 'tableHeader' },  " : "  , invoice.legalResponsibleData],
                    style: 'floatLeft'
                },
                {
                    text: [ {text: i18n.issuedDate, style: 'tableHeader' },  " : "  , formatDate(invoice.issueDate)],
                    style: 'floatLeft'
                },
                {
                    text: [ { text: i18n.period, style: 'tableHeader'}, " : " , formatDate(invoice.issueDate) + " - " + formatDate(invoice.endDate)],
                    style: 'floatLeft'
                },
                {
                    text: [ 
                        { text: i18n.invoiceTransactionStatus, style: 'tableHeader'}, " : " ,
                        { 
                            text: capitalize(invoice.transactionStatus),
                            style: (invoice.transactionStatus == 'approved') ? 'textSuccess' : 'textDanger'
                        }],
                    style: 'floatLeft'
                },
                {
                    style: 'tableInvoiceLst',
                    layout: 'lightHorizontalLines',
                    table: {
                        headerRows: 1,
                        widths: ['*', '*', '*', '*'],
                        heights: ['*', 50, '*'],
                        body: [
                            [{text: i18n.servicePlan, style: 'tableHeader'}, {text: i18n.limit, style: 'tableHeader'}, {text: i18n.type , style: 'tableHeader'}, {text: i18n.price , style: 'tableHeader'}],
                            [invoice.servicePlanName, generateMultiplyLockString(), {text: i18n.month, style: 'tableHeader'}, getPriceCurrencyFormatted(getServicePlanPrice())],
                            createProRataTable()
                        ]
                    },
                    
                },
                {
                    style: 'tableInvoiceLst',
                    layout: 'lightHorizontalLines',
                    table: {
                        headerRows: 1,
                        widths: [ '*', 'auto'],
                        body: [
                            [null, 
                                {text: i18n.total, style: 'tableHeader'}
                            ],
                            [null, getPriceCurrencyFormatted(invoice.invoiceValue)]
                        ]
                    },
                    
                },
            ],
            styles: {
                header: {
                    fontSize: 20,
                    bold: true
                },
                subheader: {
                    fontSize: 15,
                    bold: true
                },
                floatRight: {
                    alignment: "right",
                    margin: [0, 0, 0, 5]
                },
                tableInvoiceLst: {
                    margin: [0, 50, 0, 10]
                },
                tableTotal: {
                     margin: [0, 0, 0, 0]
                },
                tableHeader: {
                    bold: true,
                    fontSize: 13,
                    color: 'black'
                },
                tableHeaderRight : {
                    bold: true,
                    fontSize: 13,
                    color: 'black',
                },
                textSuccess : {
                    color: '#155724',
                    bold: true
                },
                textDanger : {
                    color: '#d03f3f',
                    bold: true
                },
                defaultStyle: {
                    columnGap: 20
                },
            }
            
        }

        return docDefinition;
    }

    return {
        download : download
    }

}(pdfMake));
