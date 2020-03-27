var ThemeCustomizer = function() {
    var e = function() {
    	/*
        var e = $(".theme-panel");
        	
        $("body").hasClass("page-boxed") === !1 && $(".layout-option", e).val("fluid"),
        $(".sidebar-option", e).val("default"),
        $(".page-header-option", e).val("fixed"),
        $(".page-footer-option", e).val("default"),
        $(".sidebar-pos-option").attr("disabled") === !1 && $(".sidebar-pos-option", e).val(App.isRTL() ? "right": "left");
        var a = function() {
            $("body").removeClass("page-boxed").removeClass("page-footer-fixed").removeClass("page-sidebar-fixed").removeClass("page-header-fixed").removeClass("page-sidebar-reversed"),
            $(".page-header > .page-header-inner").removeClass("container"),
            1 === $(".page-container").parent(".container").size() && $(".page-container").insertAfter("body > .clearfix"),
            1 === $(".page-footer > .container").size() ? $(".page-footer").html($(".page-footer > .container").html()) : 1 === $(".page-footer").parent(".container").size() && ($(".page-footer").insertAfter(".page-container"), $(".scroll-to-top").insertAfter(".page-footer")),
            $(".top-menu > .navbar-nav > li.dropdown").removeClass("dropdown-dark"),
            $("body > .container").remove()
        },
        o = "",
        t = function() {
            var t = $(".layout-option", e).val(),
            i = $(".sidebar-option", e).val(),
            d = $(".page-header-option", e).val(),
            r = $(".page-footer-option", e).val(),
            s = $(".sidebar-pos-option", e).val(),
            n = $(".sidebar-style-option", e).val(),
            p = $(".sidebar-menu-option", e).val(),
            l = $(".page-header-top-dropdown-style-option", e).val();
            if ("fixed" == i && "default" == d && (alert("Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar."), $(".page-header-option", e).val("fixed"), $(".sidebar-option", e).val("fixed"), i = "fixed", d = "fixed"), a(), "boxed" === t) {
                $("body").addClass("page-boxed"),
                $(".page-header > .page-header-inner").addClass("container");
                $("body > .clearfix").after('<div class="container"></div>');
                $(".page-container").appendTo("body > .container"),
                "fixed" === r ? $(".page-footer").html('<div class="container">' + $(".page-footer").html() + "</div>") : $(".page-footer").appendTo("body > .container")
            }
            o != t && App.runResizeHandlers(),
            o = t,
            "fixed" === d ? ($("body").addClass("page-header-fixed"), $(".page-header").removeClass("navbar-static-top").addClass("navbar-fixed-top")) : ($("body").removeClass("page-header-fixed"), $(".page-header").removeClass("navbar-fixed-top").addClass("navbar-static-top")),
            $("body").hasClass("page-full-width") === !1 && ("fixed" === i ? ($("body").addClass("page-sidebar-fixed"), $("page-sidebar-menu").addClass("page-sidebar-menu-fixed"), $("page-sidebar-menu").removeClass("page-sidebar-menu-default"), Layout.initFixedSidebarHoverEffect()) : ($("body").removeClass("page-sidebar-fixed"), $("page-sidebar-menu").addClass("page-sidebar-menu-default"), $("page-sidebar-menu").removeClass("page-sidebar-menu-fixed"), $(".page-sidebar-menu").unbind("mouseenter").unbind("mouseleave"))),
            "dark" === l ? $(".top-menu > .navbar-nav > li.dropdown").addClass("dropdown-dark") : $(".top-menu > .navbar-nav > li.dropdown").removeClass("dropdown-dark"),
            "fixed" === r ? $("body").addClass("page-footer-fixed") : $("body").removeClass("page-footer-fixed"),
            "compact" === n ? $(".page-sidebar-menu").addClass("page-sidebar-menu-compact") : $(".page-sidebar-menu").removeClass("page-sidebar-menu-compact"),
            "hover" === p ? "fixed" == i ? ($(".sidebar-menu-option", e).val("accordion"), alert("Hover Sidebar Menu is not compatible with Fixed Sidebar Mode. Select Default Sidebar Mode Instead.")) : $(".page-sidebar-menu").addClass("page-sidebar-menu-hover-submenu") : $(".page-sidebar-menu").removeClass("page-sidebar-menu-hover-submenu"),
            App.isRTL() ? "left" === s ? ($("body").addClass("page-sidebar-reversed"), $("#frontend-link").tooltip("destroy").tooltip({
                placement: "right"
            })) : ($("body").removeClass("page-sidebar-reversed"), $("#frontend-link").tooltip("destroy").tooltip({
                placement: "left"
            })) : "right" === s ? ($("body").addClass("page-sidebar-reversed"), $("#frontend-link").tooltip("destroy").tooltip({
                placement: "left"
            })) : ($("body").removeClass("page-sidebar-reversed"), $("#frontend-link").tooltip("destroy").tooltip({
                placement: "right"
            })),
            Layout.fixContentHeight(),
            Layout.initFixedSidebar() 
        },
        
        $("body").hasClass("page-boxed") && $(".layout-option", e).val("boxed"),
        $("body").hasClass("page-sidebar-fixed") && $(".sidebar-option", e).val("fixed"),
        $("body").hasClass("page-header-fixed") && $(".page-header-option", e).val("fixed"),
        $("body").hasClass("page-footer-fixed") && $(".page-footer-option", e).val("fixed"),
        $("body").hasClass("page-sidebar-reversed") && $(".sidebar-pos-option", e).val("right"),
        $(".page-sidebar-menu").hasClass("page-sidebar-menu-light") && $(".sidebar-style-option", e).val("light"),
        $(".page-sidebar-menu").hasClass("page-sidebar-menu-hover-submenu") && $(".sidebar-menu-option", e).val("hover");
        $(".sidebar-option", e).val(),
        $(".page-header-option", e).val(),
        $(".page-footer-option", e).val(),
        $(".sidebar-pos-option", e).val(),
        $(".sidebar-style-option", e).val(),
        $(".sidebar-menu-option", e).val();
        $(".layout-option, .page-header-top-dropdown-style-option, .page-header-option, .sidebar-option, .page-footer-option, .sidebar-pos-option, .sidebar-style-option, .sidebar-menu-option", e).change(t)
        */ 
    },
    loadToggler = function(){
    	// 打开面板事件
        $(".toggler", e).click(function() {
            $(".toggler").hide();
            $(".toggler-close").show();
            $(".theme-panel > .theme-options").show();
        });
        
        // 隐藏面板事件
        $(".toggler-close", e).click(function() {
            $(".toggler").show();
            $(".toggler-close").hide();
            $(".theme-panel > .theme-options").hide();
        });
    },
    loadColor = function(){
    	// 初始化 theme style  
        $(".theme-panel .theme-colors > ul > li").each(function() {
            var a = $(this).attr("data-style");
            if($("#style_color").attr("href").indexOf(a) > 0){
            	$("ul > li", e).removeClass("current");
            	$(this).addClass("current");
            	return false; // 相当于break
            }
        });
        
        // theme style 切换事件
        $(".theme-panel .theme-colors > ul > li").click(function() {
            var themeStyle = $(this).attr("data-style");
            if(App.isRTL()){ 
            	themeStyle += "-rtl";
            }
            $("#style_color").attr("href", Layout.getLayoutCssPath() + "themes/" + themeStyle + ".min.css");
            if("undefined" != typeof Cookies) { 
            	Cookies.set("theme-color", themeStyle);
            }
            $(".theme-panel ul > li").removeClass("current"),
            $(this).addClass("current")
        });
    },
    loadStyle = function(){
    	var componentName = "square";
    	// 初始化 components
        if($("#style_components").attr("href").indexOf("components-rounded") > 0){
        	componentName = "rounded";
        }
        $(".theme-panel .layout-style-option").val(componentName);
        
        // components 切换事件
        $(".theme-panel .layout-style-option").change(function() {
        	var styleName = "rounded" === $(this).val() ? "components-rounded": "components";
        	if(App.isRTL()){
        		styleName += "-rtl";
        	} 
        	$("#style_components").attr("href", App.getGlobalCssPath() + styleName + ".min.css");
        	if("undefined" != typeof Cookies) { 
        		Cookies.set("layout-style-option", $(this).val());
        	}
        });
    };
    return {
        init: function() {
            e();
            loadToggler();
            loadColor();
            loadStyle();
        }
    }
} ();
App.isAngularJsApp() === !1 && jQuery(document).ready(function() {
	ThemeCustomizer.init()
});