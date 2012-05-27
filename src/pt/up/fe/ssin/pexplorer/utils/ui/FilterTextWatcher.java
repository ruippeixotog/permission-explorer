/*
 * Copyright (C) 2012 Rui Gonçalves and Daniel Cibrão
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.up.fe.ssin.pexplorer.utils.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Filterable;

public class FilterTextWatcher implements TextWatcher {
	private Filterable filterable;

	public FilterTextWatcher(Filterable filterable) {
		this.filterable = filterable;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		filterable.getFilter().filter(s);
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}
}
