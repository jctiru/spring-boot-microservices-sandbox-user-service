package io.jctiru.springbootmicroservicessandboxuserservice.io.external;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.AlbumResponseModel;

@FeignClient(name = "album-service", fallback = AlbumFallback.class)
public interface AlbumServiceClient {

	@GetMapping("/users/{userId}/albumss")
	public List<AlbumResponseModel> getAlbums(@PathVariable String userId);

}

@Component
class AlbumFallback implements AlbumServiceClient {

	@Override
	public List<AlbumResponseModel> getAlbums(String userId) {
		return new ArrayList<>();
	}

}