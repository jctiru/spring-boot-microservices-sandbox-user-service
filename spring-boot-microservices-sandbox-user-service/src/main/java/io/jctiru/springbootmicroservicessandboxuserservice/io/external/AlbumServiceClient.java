package io.jctiru.springbootmicroservicessandboxuserservice.io.external;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.hystrix.FallbackFactory;
import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.AlbumResponseModel;

@FeignClient(name = "album-service", fallbackFactory = AlbumFallbackFactory.class)
public interface AlbumServiceClient {

	@GetMapping("/users/{userId}/albumss")
	public List<AlbumResponseModel> getAlbums(@PathVariable String userId);

}

@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {

	@Override
	public AlbumServiceClient create(Throwable cause) {
		return new AlbumServiceClientFallback(cause);
	}

}

class AlbumServiceClientFallback implements AlbumServiceClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public AlbumServiceClientFallback(Throwable cause) {
		logger.error(cause.getLocalizedMessage());
	}

	@Override
	public List<AlbumResponseModel> getAlbums(String userId) {
		return new ArrayList<>();
	}

}