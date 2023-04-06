package com.masai.modeldto;

import java.util.List;

import com.masai.models.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apidata {

	private Integer count;

	private List<Entry> entries;
}
