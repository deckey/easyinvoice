

function autoInvoiceNumber() {
    var d = $("#invoiceIssueDate").val();
    var date = d.split("/");
    var month = date[0];
    var day = date[1];
    if(day.length==1){
        day = "0"+day;
    }
    var year = date[2]
    dateFormat = year+"-"+month+day;
    $("#invoiceNumber").val(dateFormat);
}