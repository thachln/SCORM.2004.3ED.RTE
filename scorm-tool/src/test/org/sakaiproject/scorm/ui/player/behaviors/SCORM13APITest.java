/*
 * #%L
 * SCORM Tool
 * %%
 * Copyright (C) 2007 - 2016 Sakai Project
 * %%
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *             http://opensource.org/licenses/ecl2
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.sakaiproject.scorm.ui.player.behaviors;

public class SCORM13APITest extends AbstractSCORM13APBase {

	public void testSimple() {
		assertEquals("true", scorm13api.Initialize(""));
		assertEquals("OBJ_FLASH", scorm13api.GetValue("cmi.objectives.0.id"));
		assertEquals("OBJ_DIRECTOR", scorm13api.GetValue("cmi.objectives.1.id"));
		// Count
		assertEquals("2", scorm13api.GetValue("cmi.objectives._count"));
		
		// 301
		assertEquals("false", scorm13api.SetValue("cmi.interactions.1.id", "koe"));
		assertEquals("301", scorm13api.GetLastError());
		
		// Count
		assertEquals("0", scorm13api.GetValue("cmi.interactions._count"));
		// Ok
		assertEquals("true", scorm13api.SetValue("cmi.interactions.0.id", "koe"));
		assertEquals("0", scorm13api.GetLastError());

		// Count
		assertEquals("1", scorm13api.GetValue("cmi.interactions._count"));
		
		scorm13api.SetValue("cmi.session_time", "PT1H5M");
		scorm13api.Commit("");
		scorm13api.SetValue("does.not.extist", "que");
		
		scorm13api.Terminate("");
	}

}
