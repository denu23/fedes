$(document).ready(function ()
{
    var $preview = $("#preview");

    $(".poster").hover(function ()
    {
    	Console.log("muie");
        $preview.attr("src", $(this).attr("data-thumbnail-src"));
    }, function ()
    {
        $preview.attr("src", "");
    });
});