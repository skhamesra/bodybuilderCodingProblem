package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.TaxonomyNode;
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
public class TaxonomyControllerTest {

    TaxonomyController controller;

    @Mock
    TaxonomyService taxonomyService;

    @Before
    public void setUp() throws Exception {
        controller = new TaxonomyController(taxonomyService);
    }

    @Test
    public void testRoot() throws Exception {

        TaxonomyNode dummyTaxonomyNode = mock(TaxonomyNode.class);
        when(taxonomyService.get()).thenReturn(dummyTaxonomyNode);

        ResponseEntity<TaxonomyNode> response = controller.root();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dummyTaxonomyNode);

    }

    @Test
    public void testSelect() throws Exception {

        String selector = "my-selector";

        // Replace this validation logic below with the logic from your implementation
        // Make sure you test all the logic branches in your code (you may want multiple tests)

        ResponseEntity<TaxonomyNode> response = controller.select(selector);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.I_AM_A_TEAPOT);
        assertThat(response.getBody()).isNull();

    }
}