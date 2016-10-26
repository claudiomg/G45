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

});





