package org.kubek2k.springockito.jira011;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.kubek2k.tools.Jira;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.fest.assertions.Assertions.assertThat;
import static org.kubek2k.springockito.annotations.ReplaceWithMock.BeanNameStrategy.FIELD_NAME;
import static org.kubek2k.tools.TestUtil.isMock;

@Jira(number = 11, uri = "/kubek2k/springockito/issue/11/an-ability-to-define-a-name-of-the")
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:spring/jira011/context_5.xml"})
public class ExplicitBeanNameMoreImportantThenBeanNameStrategy_Test {

    @Resource(name = "beanWantingExplicitName")
    private BeanInjectedWith beanWantingExplicitName;

    @ReplaceWithMock(beanName = "firstBeanNotInContextArbitraryName", beanNameStrategy = FIELD_NAME)
    private FirstBeanNotInContext firstBean;

    @Test
    public void shouldMockBasedOnBeanName() {
        FirstBeanNotInContext injected = beanWantingExplicitName.getFirstBeanNotInContext();
        assertThat(isMock(injected))
                .isTrue();
    }
}
