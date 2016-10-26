$(document).ready(function() {
	function updatePoiFilter(){
		var isManual = $("#searchMode").is(":checked");
		//$(".advancedFilters .poiFilters input").prop('checked', isManual);
		if(isManual){
			$(".advancedFilters").hide();
		}else{
			$(".advancedFilters").show();
		}
	};
	$("#searchMode").checkboxpicker({
		html: true,
		offLabel: 'Avanzado',
		onLabel: 'Básico',
		offActiveCls: 'btn-primary',
		onActiveCls: 'btn-primary'
	});
	$("#searchMode").change(updatePoiFilter);
	
	updatePoiFilter();
	
	$('#slide-submenu').on('click',function() {			        
		$(this).closest('.list-group').fadeOut('slide',function(){
			$('.mini-submenu').fadeIn();	
		});
	});
	
	$('.mini-submenu').on('click',function(){		
		$(this).next('.list-group').toggle('slide');
		$('.mini-submenu').hide();
	});
	
	jQuery(function ($) {
        // init the state from the input
        $(".advancedFilters .image-checkbox").each(function () {
            if ($(this).find('input[type="checkbox"]').first().attr("checked")) {
                $(this).addClass('image-checkbox-checked');
            }
            else {
                $(this).removeClass('image-checkbox-checked');
            }
        });

        // sync the state to the input
        $(".advancedFilters .image-checkbox").on("click", function (e) {
            if ($(this).hasClass('image-checkbox-checked')) {
                $(this).removeClass('image-checkbox-checked');
                $(this).find('input[type="checkbox"]').first().removeAttr("checked");
            }
            else {
                $(this).addClass('image-checkbox-checked');
                $(this).find('input[type="checkbox"]').first().attr("checked", "checked");
            }

            e.preventDefault();
        });
    });
});





