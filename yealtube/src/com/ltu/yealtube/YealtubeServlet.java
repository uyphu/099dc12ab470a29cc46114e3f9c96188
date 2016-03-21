package com.ltu.yealtube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.ltu.yealtube.dao.RemoteObjectifyFactory;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.domain.Thing;
import com.ltu.yealtube.service.AuthorityService;
import com.ltu.yealtube.service.ThingService;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static com.googlecode.objectify.ObjectifyService.run;

@SuppressWarnings("serial")
public class YealtubeServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		ObjectifyService.setFactory(new RemoteObjectifyFactory());
	}
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
//		resp.setContentType("text/plain");
//		resp.getWriter().println("Hello, world");
		
		run(new VoidWork() {
			
			@Override
			public void vrun() {
				//ObjectifyService.register(Thing.class);
//				ObjectifyService.setFactory(new RemoteObjectifyFactory());
				//ObjectifyFactory factory = ObjectifyService.factory();
//				factory = ofy().factory();
//				Thing thing = new Thing(21L, "test32423");
//				ObjectifyService.ofy().save().entity(thing).now();
//
//				ofy().clear();

				Thing fetched = ofy().load().type(Thing.class).id(20L).now();

//				write(resp, "Thing before roundtrip: " + thing);
				write(resp, "Thing after roundtrip: " + fetched);
			}
		});
		
//		Thing thing = new Thing(3L, "test");
//		ofy().save().entity(thing).now();
//
//		ofy().clear();
//
//		Thing fetched = ofy().load().entity(thing).now();
//
//		write(resp, "Thing before roundtrip: " + thing);
//		write(resp, "Thing after roundtrip: " + fetched);
		
		
//		try {
//			ThingService thingService = ThingService.getInstance();
//			Thing thing = new Thing("teetssdfdsa");
//			//thingService.insertThing(thing);
//			thing = thingService.find(10L);
//			System.out.println(thing.toString());
//			List<Thing> list = thingService.findAll();
//			for (Thing thing2 : list) {
//				System.out.println(thing2);
//			}
//			AuthorityService authorityService = AuthorityService.getInstance();
//			CollectionResponse<Authority> list = authorityService.listAuthority(null, null);
//			for (Authority authority : list.getItems()) {
//				System.out.println(authority);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	
	private void write(HttpServletResponse resp, String someText) {
		try {
			resp.getWriter().println(someText);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
