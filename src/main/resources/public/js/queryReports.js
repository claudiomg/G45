$('document').ready(function() {
	$("#selectReport").change(function() {
		var data = $(this).val();
		var url = "/query_reports";
		if (data) {
			url = url.concat("/").concat(data);
		}
		window.location = url;
	    return false;
	});
});

function createAdvancedReportView(){
	Morris.Line({
		  element: 'advancedReportView',
		  data: [
		    { y: '2006', a: 100, b: 90 },
		    { y: '2007', a: 75,  b: 65 },
		    { y: '2008', a: 50,  b: 40 },
		    { y: '2009', a: 75,  b: 65 },
		    { y: '2010', a: 50,  b: 40 },
		    { y: '2011', a: 75,  b: 65 },
		    { y: '2012', a: 100, b: 90 }
		  ],
		  xkey: 'y',
		  ykeys: ['a', 'b'],
		  labels: ['Series A', 'Series B']
		});
}