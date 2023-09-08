package com.cokkiri.secondhand.item.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ItemListResponse {

	private String userLocation;
	private List<ItemResponse> items;
	private Long nextCursor;
}
