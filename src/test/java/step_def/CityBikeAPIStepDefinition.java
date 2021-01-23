package step_def;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import com.qa.api.base.TestBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CityBikeAPIStepDefinition extends TestBase {
	RequestSpecification request;
	Response resp;

	@Given("accept json format")
	public void accept_json_format() throws IOException {
		// set the base url
		RestAssured.baseURI = readPropertyValue("baseUrl");
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Accept", "application/json");
	}

	@When("I do Get request to fetch all networks endpoint")
	public void i_do_get_request_to_fetch_all_networks_endpoint() {
		// request the GET call
		resp = request.request(Method.GET, "");
		System.out.println("response body is:" + resp.getBody().asString());

	}

	@Then("response status code should be {string}")
	public void response_status_code_should_be(String statusCode) {
		// fetch the response status code
		int respCode = resp.getStatusCode();
		Assert.assertEquals(respCode, Integer.parseInt(statusCode));

	}

	@When("I do Get request with href value")
	public void I_do_Get_request_with_href_value() {
		// request the GET call
		resp = request.request(Method.GET, "/visa-frankfurt");
		System.out.println("response body is:" + resp.getBody().asString());

	}

	@Then("i should see response status code should be {string}")
	public void i_should_see_response_status_code_should_be(String statusCode) {
		// fetch the response status code
		int respCode = resp.getStatusCode();
		if (respCode == 200) {
			Assert.assertEquals(respCode, Integer.parseInt(statusCode));
		} else if (respCode == 404) {
			Assert.assertEquals(respCode, Integer.parseInt(statusCode));
		}
	}

	@Then("I verify the city Frankfurt is in Germany")
	public void I_verify_the_city_Frankfurt_is_in_Germany() {
		// fetch the city and country code
		String cityVal = resp.jsonPath().get("network.location.city");
		System.out.println("city value is:" + cityVal);
		Assert.assertEquals(cityVal, "Frankfurt");
		// fetch the city Germany country code
		String countryCode = resp.jsonPath().get("network.location.country");
		System.out.println("country value is:" + countryCode);
		Assert.assertEquals(countryCode, "DE");

	}

	@Then("I get the city corresponding latitude and longitude")
	public void I_get_the_city_corresponding_latitude_and_longitude() {
		// fetch the city and country code
		Float latitudeVal = resp.jsonPath().get("network.location.latitude");
		System.out.println("Frankfurt latitude value: " + latitudeVal);
		// fetch the city Germany country code
		Float longtudeVal = resp.getBody().jsonPath().get("network.location.longitude");
		System.out.println("Frankfurt longitude value: " + longtudeVal);
	}

	@When("I do Get request to fetch network by valid networkid")
	public void I_do_Get_request_to_fetch_network_by_valid_networkid() {

		resp = request.request(Method.GET, "/velobike-moscow");
		System.out.println("Fetch network by valid networkid - response body is:" + resp.getBody().asString());

	}

	@When("I do Get request to fetch the network by invalidnetworkid endpoint")
	public void I_do_Get_request_to_fetch_the_network_by_invalidnetworkid_endpoint() {
		resp = request.request(Method.GET, "/f5a551a87eec15155d6409fe9d0ff8e2");
		System.out.println("Fetch the network by invalidnetworkid - response body is:" + resp.getBody().asString());
	}

	@When("I do Get request with field filters id,name,href endpoint")
	public void I_do_Get_request_with_field_filters_id_name_href_endpoint() {
		resp = request.request(Method.GET, "?fields=id,name,href");
		System.out.println("field filters id,name,href endpoint -- response body is:" + resp.getBody().asString());
	}

}
