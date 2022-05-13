/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validatePaymentForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UnitAPI",
 type : type,
 data : $("#formUnit").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onEmployeeSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).data("ID"));
 $("#AccountNo").val($(this).closest("tr").find('td:eq(0)').text());
 $("#CusName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Unit").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Month").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Amount").val($(this).closest("tr").find('td:eq(4)').text());

});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "UnitAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onPaymentDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// account number
if ($("#AccountNo").val().trim() == "")
 {
 return "Insert Account Number.";
 }
// NAME
if ($("#CusName").val().trim() == "")
 {
 return "Insert Customer Name.";
 } 
 // Power Units------------------------
if ($("#Unit").val().trim() == "")
 {
 return "Insert Power Units.";
 }
 // payment Month------------------------
if ($("#Month").val().trim() == "")
 {
 return "Insert payment Date.";
 }
 

//  amount-------------------------------
if ($("#Amount").val().trim() == "")
 {
 return "Insert Amount.";
 }
// is numerical value
var tmpAmt = $("#Amount").val().trim();
if (!$.isNumeric(tmpAmt))
 {
 return "Insert a numerical value for  amount.";
 }
// convert to decimal price
//$("#Amount").val(parseDouble(tmpAmt).toFixed(2));


return true;
}

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidIDSave").val("");
 $("#formEmployee")[0].reset();
}

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 