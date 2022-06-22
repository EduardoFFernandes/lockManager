/*
Template Name: Velonic - Responsive Bootstrap 4 Admin Dashboard
Author: CoderThemes
Email: support@coderthemes.com
Version: 4.0.0
Date: Jan 2015
Last Update: May 2018
File: Main App Js
*/

(function ($) {

    'use strict';

    function initSlimscrollMenu() {

        $('.slimscroll-menu').slimscroll({
            height: 'auto',
            position: 'right',
            size: "5px",
            color: '#9ea5ab',
            wheelStep: 5,
            touchScrollStep: 50
        });
    }

    function initSlimscroll() {
        $('.slimscroll').slimscroll({
            height: 'auto',
            position: 'right',
            size: "5px",
            color: '#9ea5ab',
            touchScrollStep: 50
        });
    }

    function initMetisMenu() {
        //metis menu
        $("#side-menu").metisMenu();
    }

    function initLeftMenuCollapse() {
        // Left menu collapse
        $('.button-menu-mobile').on('click', function (event) {
            event.preventDefault();
            $("body").toggleClass("enlarged");
            $("#sidebar-menu li.active:first").find("ul.nav-second-level").toggleClass("in");
        
        });
    }

    function initEnlarge() {
        var windowWidth = $(window).width();
        if (windowWidth < 1025) {
            $('body').addClass('enlarged');

            var pageSize = $('body .content-page').width();
            var navBarSize = $('header .navbar-custom').width();
            var logoSize = 70;
            var padding = 20;
            if(navBarSize > pageSize){
                $('header .topbar').width(windowWidth)
                $('header .navbar-custom').width(windowWidth - (logoSize + padding));
            }
        } else {
            $('body').removeClass('enlarged');
        }
    }

    function initActiveMenu() {
        // === following js will activate the menu in left side bar based on url ====
        $("#sidebar-menu a").each(function () {
            var href = $(this).attr('href').replace(/#/g, '');
            if(href.length == 0) return;
            
            var pageUrl = window.location.href.split(/[?#]/)[0];
            var hrefUrl = this.href.replace(/#/g, '');

            if (isMenuLinkActive(hrefUrl, pageUrl)) {
                $(this).addClass("active");
                $(this).parent().addClass("active"); // add active to li of the current link
                $(this).parent().parent().addClass("in");
                $(this).parent().parent().prev().addClass("active"); // add active class to an anchor
                $(this).parent().parent().parent().addClass("active");
                $(this).parent().parent().parent().parent().addClass("in"); // add active to li of the current link
                $(this).parent().parent().parent().parent().parent().addClass("active");
            } else if (! pageUrl.includes('index')) {
                $(this).parent().removeClass("active");
            }
        });
        $("body.enlarged #sidebar-menu li.active:first").find("ul.nav-second-level").toggleClass("in");
    }

    function isMenuLinkActive(hrefUrl, pageUrl){
        if(hrefUrl == pageUrl || pageUrl.includes(hrefUrl.replace('list', 'edit')) || pageUrl.includes('admin/dashboard/index')){
            return true;
        } else if(
            hrefUrl.includes('/lock/list') && 
            (
                pageUrl.includes('/lock/edit') || pageUrl.includes('/lock/setting') ||
                pageUrl.includes('/lock/event') || pageUrl.includes('/lock/access')
            )
        ){
            return true;
        }

        return false;
    }

    function init() {
        initSlimscrollMenu();
        initSlimscroll();
        initMetisMenu();
        initLeftMenuCollapse();
        initEnlarge();
        initActiveMenu();
    }

    init();

})(jQuery)
