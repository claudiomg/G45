
$(document).ready(function(){
    $("input:checkbox").change(function() { 
        var isChecked = $(this).is(":checked") ? true:false; 
        $.ajax({
            url: '/update_terminal_configuration',
            type: 'POST',
            data: { terminal:$(this).attr("terminal"), action:$(this).attr("name"),value:isChecked }
        });        
    });        
});