package joao.alarcon.tests.kabum.analytics.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import joao.alarcon.tests.kabum.analytics.bo.VisualAnalyticsBO;
import joao.alarcon.tests.kabum.analytics.model.MerchandiseLine;

@RestController
@RequestMapping("/testapi/analytics")
public class VisualAnalyticsApi 
{
	@Autowired
	private VisualAnalyticsBO visualAnalyticsBo;
	
	@CrossOrigin(origins = "*")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataAnalytics()
	{
		List<MerchandiseLine> merchandiseLines = null;
		merchandiseLines = visualAnalyticsBo.readXLSData();
		Gson gson = new Gson();
		String merchandiseLineJson = gson.toJson(merchandiseLines);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<String>("{\"categories\": " + merchandiseLineJson + "}", 
				httpHeaders, HttpStatus.OK);
	}
}
