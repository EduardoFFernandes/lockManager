Vue.component('vue-select2', {
  props: ['options', 'value'],
  template: '<select><slot></slot></select>',
  mounted: function () {
    var vm = this
    $(this.$el)
      .select2({ data: this.options })
      .val(this.value)
      .trigger('change')
      .on('change', function () {
        vm.$emit('input', this.value)
      })
  },
  methods:{
    updateSelected: function() {
      $(this.$el).val(this.value).trigger('change', this.value)
    }
  },
  watch: {
    value: function (value) {
      $(this.$el).val(value).trigger('change')
    },
    options: function (options) {
      $(this.$el).empty().select2({ data: options })
      this.updateSelected();
    }
  },
  destroyed: function () {
    $(this.$el).off().select2('destroy')
  }
})

Vue.component('vue-full-calendar', {
  template: 
  `
  <div >
    <full-calendar ref="fullCalendar" id="calendar"
        default-view="timeGridWeek"
        :plugins="calendarPlugins" 
        :header="header"
        :editable="editable"
        :selectable="selectable"
        :allDaySlot="allDaySlot"
        :firstDay="firstDay"
        :slotLabelInterval="slotLabelInterval"
        :slotDuration="slotDuration"
        :select-overlap="selectOverlap"
        :selectMirror="selectMirror"
        :eventOverlap="eventOverlap"
        :selectConstraint="selectConstraint"
        :eventTimeFormat="eventTimeFormat"
        :columnHeaderText="columnHeaderText"
        :height="height"
        :longPressDelay="longPressDelay"
        :eventLongPressDelay="eventLongPressDelay"
        :selectLongPressDelay="selectLongPressDelay"
        @select="select"
        @eventMouseEnter="eventMouseEnter"
        @eventResize="eventResize"
        @eventMouseLeave="eventMouseLeave"
        @eventDragStart="eventDragStart"
        @eventDrop="eventDrop"
    />
  </div>
  `,
  mounted(){
    if(this.calendarInstance == null){
      this.calendarInstance = this.$refs.fullCalendar.getApi();

      this.calendarInstance.emitDelete = function(element){ 
        this.$emit('event-deleted', element.data().id)
      }.bind(this);

      this.$emit('calendar-mounted', { calendarInstance: this.calendarInstance, calendarComponentInstance: this})
    }
  },
  methods:{
    addEvent(event){
      this.eventsId = (this.calendarInstance.getEvents().length == 0) ? 0 : this.eventsId
      var event= this.calendarInstance.addEvent({
        id: this.eventsId,
        start: event.start,
        end: event.end,
        allDay: false,
        className: `event-id-${this.eventsId}`,
        extendedProps: {
          weekday: moment(event.start).isoWeekday(),
          inputId: event.inputId ? event.inputId : null
        },
        startEditable: true
      })
      this.createTooltipter();
      return event;
    },
    createTooltipter(id = undefined, inputId){
      eventsId = (typeof id == "undefined") ? this.eventsId : id;
      inputId = (typeof inputId == "undefined") ? null : inputId;
      var tooltipster = $(`.event-id-${eventsId}`).tooltipster({
        contentAsHTML: true,
        interactive: true,
        theme: 'tooltipster-shadow',
        animation: 'grow',
        delay: 200,
        touchDevices: true,
        trigger: 'hover',
        arrow: true,
        side: 'top',
        content: 
        `
          <span data-id="${inputId}" 
                onclick="calendarInstance.getEventById(${eventsId}).remove(); $(this).parent().parent().parent().hide(); calendarInstance.emitDelete($(this))" 
                style="cursor: pointer; z-index: -1;">
            <a class="mdi mdi-trash-can-outline" ></a> Delete
          </span>
        `,
        functionFormat: function(instance, helper, content) {
          return content
        }
      });
      if(typeof id == "undefined"){
        this.eventsId += 1;
      }
      return tooltipster;
    },
    select(arg){
      var start = moment(arg.start);
      var end = moment(arg.end);
      var isSameDay = end.isSame(start, 'day')
      if(isSameDay != true){
        this.calendarInstance.unselect()
        return;
      }
      var event = this.addEvent(arg)
      this.calendarInstance.unselect()
      
    },
    eventMouseEnter(calendarEvent){
      var hasTooltipster = $(calendarEvent.el).hasClass("tooltipstered")
      if(!hasTooltipster){
        var newTooltipter = this.createTooltipter(calendarEvent.event.id, calendarEvent.event.extendedProps.inputId)
        newTooltipter.tooltipster('open')
      } else if(hasTooltipster){
        $(calendarEvent.el).tooltipster('open')
      }
    },
    eventResize(calendarEvent){
      if(calendarEvent && calendarEvent.event){
        var event = calendarEvent.event;
        var start = moment(event.start);
        var end = moment(event.end);
        var isSameDay = end.isSame(start, 'day')
        var isSameHour = (start.hour() == end.hour())
        if(isSameDay == false && isSameHour == true){
          end = end.subtract(1, 'minute');
          calendarEvent.event.setEnd(end.format())
        }
      }
    },
    eventMouseLeave(calendarEvent){
    },
    eventDragStart(calendarEvent){
    },
    eventDrop(calendarEvent){
    },
    getHeight() {
        var height = 650;
        if (isMobile()){
            height = 'auto';
        }
        return height;
    }
  },
  data() {
    return{
      eventDragSave: null,
      eventsId: 0,
      calendarInstance: null,
      calendarPlugins: [ 
        FullCalendarDayGrid.default, 
        FullCalendarInteraction.default,
        FullCalendarTimeGrid.default
      ],
      selectMirror: true,
      header: false,
      editable: true,
      selectable: true,
      allDaySlot: false,
      columnHeaderText: function(date) {
          var format = 'dddd';
          if (isMobile()){
              format = 'dd';
          }
          return moment(date).format(format);
      },
      eventTimeFormat: {
        hour: 'numeric',
        minute: '2-digit',
        meridiem: 'short'
      },
      firstDay: 1,
      slotLabelInterval: '01:00',
      slotDuration: '00:30:00',
      eventOverlap: false,
      selectOverlap: false,
      selectConstraint: {
        startTime: '00:00',
        endTime: '23:59',
      },
      longPressDelay: 500,
      eventLongPressDelay: 500,
      selectLongPressDelay: 500,
      height: this.getHeight()
    }
  }
});
