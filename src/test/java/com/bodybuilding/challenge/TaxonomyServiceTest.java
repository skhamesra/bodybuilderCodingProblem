package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.TaxonomyDao;
import com.bodybuilding.challenge.data.TaxonomyNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxonomyServiceTest {

    TaxonomyService service;

    @Mock
    TaxonomyDao taxonomyDao;

    @Before
    public void setUp() throws Exception {

        service = new TaxonomyService(taxonomyDao);
    }

    @Test
    public void testGet() throws Exception {
        TaxonomyNode dummyRootNode = mock(TaxonomyNode.class);

        when(taxonomyDao.getRoot()).thenReturn(dummyRootNode);

        TaxonomyNode actual = service.get();

        assertThat(actual).isNotNull().isSameAs(dummyRootNode);
    }

    // TODO
    // Add unit test for the function you add to the
    // TaxonomyService
}