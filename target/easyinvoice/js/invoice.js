function changeInvoiceBgnd() {
    var status = $(document.getElementsByName('invoiceStatus')).val();
    if (status == "Open") {
        $('#editInvoice').removeClass('closed');
        $('#editInvoice').removeClass('overdue');
    }
    if (status == "Overdue") {
        $('#editInvoice').removeClass('closed');
        $('#editInvoice').addClass('overdue');
    }
    if (status == "Closed") {
        $('#editInvoice').removeClass('overdue');
        $('#editInvoice').addClass('closed');
    }
}
$('#editInvoiceBtn').click(function () {
    $("#editInvoiceForm").submit();
});

$(function () {
    changeInvoiceBgnd();
});