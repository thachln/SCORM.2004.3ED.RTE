/**
 * Copyright (c) 2007 The Apereo Foundation
 *
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
 */
package org.sakaiproject.scorm.ui.player;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class HibernateFilter extends OpenSessionInViewFilter {

	@Override
	protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		//set the FlushMode to auto in order to save objects.
		session.setFlushMode(FlushMode.AUTO);
		return session;
	}


	@Override
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		try{
			if (session != null && session.isOpen() && session.isConnected()) {
				try {
					session.flush();
				} catch (HibernateException e) {
					throw new CleanupFailureDataAccessException("Failed to flush session before close: " + e.getMessage(), e);
				} catch(Exception e){}
			}
		} finally{
			super.closeSession(session, sessionFactory);
		}
	}
}
