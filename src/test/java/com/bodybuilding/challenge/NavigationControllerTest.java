package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.NavigationDao;
import com.bodybuilding.challenge.data.NavigationNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NavigationControllerTest {

    NavigationController controller;

    @Mock
    NavigationDao dao;


    @Before
    public void setUp() throws Exception {
        controller = new NavigationController(dao);
    }

    @Test
    public void testRoot() throws Exception {

        NavigationNode dummyNavigationNode = mock(NavigationNode.class);
        when(dao.getRoot()).thenReturn(dummyNavigationNode);

        ResponseEntity<NavigationNode> response = controller.root();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dummyNavigationNode);

    }

    @Test
    public void testSelect() throws Exception {

        when(dao.findAndPrune("test")).thenReturn(new NavigationNode("id", "name", "url"));
        ResponseEntity<NavigationNode> response = controller.select("test");
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testSelect_notFound() throws Exception {

        when(dao.findAndPrune("test")).thenReturn(null);
        ResponseEntity<NavigationNode> response = controller.select("test");
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }
}