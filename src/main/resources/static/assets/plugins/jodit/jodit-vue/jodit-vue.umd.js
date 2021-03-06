(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
  typeof define === 'function' && define.amd ? define(['exports'], factory) :
  (global = global || self, factory(global.JoditVue = {}));
}(this, (function (exports) { 'use strict';

  /* eslint-env node */
  var GlobalJodit = null;

  if (typeof window !== 'undefined') { GlobalJodit = window.Jodit; }
  else if (typeof global !== 'undefined') { GlobalJodit = global.Jodit; }

  var Jodit = GlobalJodit;

  //

  var script = {
    name: 'JoditVue',

    props: {
      value: { type: String, required: true },
      buttons: { type: Array, default: null },
      extraButtons: { type: Array, default: null },
      config: { type: Object, default: function () { return ({}); } }
    },

    data: function () { return ({ editor: null }); },

    computed: {
      editorConfig: function editorConfig () {
        var config = Object.assign({}, this.config);

        if (this.buttons) {
          config.buttons = this.buttons;
          config.buttonsMD = this.buttons;
          config.buttonsSM = this.buttons;
          config.buttonsXS = this.buttons;
        }

        if (this.extraButtons) { config.extraButtons = this.extraButtons; }
        return config
      }
    },

    watch: {
      value: function value (newValue) {
        if (this.editor.value !== newValue) { this.editor.value = newValue; }
      }
    },

    mounted: function mounted () {
      var this$1 = this;

      this.editor = new Jodit(this.$el, this.editorConfig);
      this.editor.value = this.value;
      this.editor.events.on('change', function (newValue) {
        var editorInnerText = this$1.editor.editor.innerText;
        if (editorInnerText.length === 0 || editorInnerText.replace(/\n/g, '').length === 0) {
          newValue = '';
        }
        this$1.$emit('input', newValue);
      });
      this.editor.events.on('focus', function (e) {
        this$1.$emit('focus', e);
      });
      this.editor.events.on('blur', function (e) {
        this$1.$emit('blur', e);
      });
      this.editor.events.on('afterSetMode', function (e) {
        this$1.$emit('change-mode', e);
      });
    },

    beforeDestroy: function beforeDestroy () {
      this.editor.destruct();
    }
  };

  function normalizeComponent(template, style, script, scopeId, isFunctionalTemplate, moduleIdentifier /* server only */, shadowMode, createInjector, createInjectorSSR, createInjectorShadow) {
      if (typeof shadowMode !== 'boolean') {
          createInjectorSSR = createInjector;
          createInjector = shadowMode;
          shadowMode = false;
      }
      // Vue.extend constructor export interop.
      var options = typeof script === 'function' ? script.options : script;
      // render functions
      if (template && template.render) {
          options.render = template.render;
          options.staticRenderFns = template.staticRenderFns;
          options._compiled = true;
          // functional template
          if (isFunctionalTemplate) {
              options.functional = true;
          }
      }
      // scopedId
      if (scopeId) {
          options._scopeId = scopeId;
      }
      var hook;
      if (moduleIdentifier) {
          // server build
          hook = function (context) {
              // 2.3 injection
              context =
                  context || // cached call
                      (this.$vnode && this.$vnode.ssrContext) || // stateful
                      (this.parent && this.parent.$vnode && this.parent.$vnode.ssrContext); // functional
              // 2.2 with runInNewContext: true
              if (!context && typeof __VUE_SSR_CONTEXT__ !== 'undefined') {
                  context = __VUE_SSR_CONTEXT__;
              }
              // inject component styles
              if (style) {
                  style.call(this, createInjectorSSR(context));
              }
              // register component module identifier for async chunk inference
              if (context && context._registeredComponents) {
                  context._registeredComponents.add(moduleIdentifier);
              }
          };
          // used by ssr in case component is cached and beforeCreate
          // never gets called
          options._ssrRegister = hook;
      }
      else if (style) {
          hook = shadowMode
              ? function (context) {
                  style.call(this, createInjectorShadow(context, this.$root.$options.shadowRoot));
              }
              : function (context) {
                  style.call(this, createInjector(context));
              };
      }
      if (hook) {
          if (options.functional) {
              // register for functional component in vue file
              var originalRender = options.render;
              options.render = function renderWithStyleInjection(h, context) {
                  hook.call(context);
                  return originalRender(h, context);
              };
          }
          else {
              // inject component registration as beforeCreate hook
              var existing = options.beforeCreate;
              options.beforeCreate = existing ? [].concat(existing, hook) : [hook];
          }
      }
      return script;
  }

  /* script */
  var __vue_script__ = script;

  /* template */
  var __vue_render__ = function() {
    var _vm = this;
    var _h = _vm.$createElement;
    var _c = _vm._self._c || _h;
    return _c("textarea")
  };
  var __vue_staticRenderFns__ = [];
  __vue_render__._withStripped = true;

    /* style */
    var __vue_inject_styles__ = undefined;
    /* scoped */
    var __vue_scope_id__ = undefined;
    /* module identifier */
    var __vue_module_identifier__ = undefined;
    /* functional template */
    var __vue_is_functional_template__ = false;
    /* style inject */
    
    /* style inject SSR */
    
    /* style inject shadow dom */
    

    
    var __vue_component__ = normalizeComponent(
      { render: __vue_render__, staticRenderFns: __vue_staticRenderFns__ },
      __vue_inject_styles__,
      __vue_script__,
      __vue_scope_id__,
      __vue_is_functional_template__,
      __vue_module_identifier__,
      false,
      undefined,
      undefined,
      undefined
    );

  /* eslint-env node */

  function install (Vue) {
    if (install.installed) { return }
    install.installed = true;
    Vue.component('JoditVue', __vue_component__);
  }

  var plugin = { install: install };
  var GlobalVue = null;

  if (typeof window !== 'undefined') { GlobalVue = window.Vue; }
  else if (typeof global !== 'undefined') { GlobalVue = global.Vue; }

  if (GlobalVue) { GlobalVue.use(plugin); }

  exports.JoditVue = __vue_component__;
  exports.default = plugin;
  exports.install = install;

  Object.defineProperty(exports, '__esModule', { value: true });

})));
