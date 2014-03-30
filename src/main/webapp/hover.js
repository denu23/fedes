$(document).ready(function ()
{
    var $preview = $("#preview");

    $(".poster").hover(function ()
    {
    	console.log("muie");
        $preview.attr("src", $(this).data("thumbnail-src"));
    }, function ()
    {
        $preview.attr("src", "");
    });
});