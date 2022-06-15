
package brisa.lockmanager.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import brisa.lockmanager.commons.constants.MessageConstant;

public class SubscriptionNotifierFilter implements Filter, MessageConstant {


    // ---------------------------------------------------------------------------------------------
    // Statics blocks
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
    // ---------------------------------------------------------------------------------------------
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(
                this,
                filterConfig.getServletContext());
    }

    // ---------------------------------------------------------------------------------------------
    // * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
    // ---------------------------------------------------------------------------------------------
    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(request, response);
    }

    // ---------------------------------------------------------------------------------------------
    // * @see javax.servlet.Filter#destroy()
    // ---------------------------------------------------------------------------------------------
    @Override
    public void destroy() {
        // Nothing to do
    }
}
