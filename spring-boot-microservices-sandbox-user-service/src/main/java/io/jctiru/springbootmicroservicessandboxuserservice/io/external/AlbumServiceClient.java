package io.jctiru.springbootmicroservicessandboxuserservice.io.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.AlbumResponseModel;

@FeignClient(name = "album-service")
public interface AlbumServiceClient {

	@GetMapping("/users/{userId}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable String userId);

}
