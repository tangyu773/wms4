/**
 * 模块管理控制器
 * Created by xiaozou on 15-10-22.
 */
Ext.define('Admin.view.widgets.WidgetController',{
	extend: 'Ext.app.ViewController',	
    alias: 'controller.Widgets',	
    requires: [
        'Ext.util.TaskRunner'
    ],
	task:undefined,	
     onDownload: function() {
        var chart = this.lookupReference('chart');
        if (Ext.os.is.Desktop) {
            chart.download({
                filename: 'Redwood City Climate Data Chart'
            });
        } else {
            chart.preview();
        }
    },

    onReloadData: function() {
        var chart = this.lookupReference('chart');
        chart.getStore().refreshData();
    },

    // The 'target' here is an object that contains information
    // about the target value when the drag operation on the column ends.
    onEditTipRender: function (tooltip, item, target, e) {
        tooltip.setHtml('Temperature °F: ' + target.yValue.toFixed(1));
    },

    onSeriesLabelRender: function (value) {
        return value.toFixed(1);
    },

    onColumnEdit: function (chart, data) {
        var threshold = 65,
            delta = 20,
            yValue = data.target.yValue,
            coldness;

        if (yValue < threshold) {
            coldness = Ext.Number.constrain((threshold - yValue) / delta, 0, 1);
            return {
                fillStyle: 'rgba(133, 231, 252, ' + coldness.toString() + ')'
            };
        } else {
            return {
                fillStyle: 'none'
            };
        }
    },

    onAfterRender: function () {
        var me = this,
            chart = this.lookupReference('chart'),
            axis = chart.getAxis(0),
            store = chart.getStore();

        function onAxisRangeChange() {
            me.onAxisRangeChange(axis);
        }

        chart.preview();
    
        store.on({
            datachanged: onAxisRangeChange,
            update: onAxisRangeChange
        });
    },
    ondAfterRender: function(cmp){
        
        var me = this,
        chart1 = this.lookupReference('chart1'),
        chart2 = this.lookupReference('chart2'),
        chart3 = this.lookupReference('chart3');

    
        var wd1 = cmp.down('displayfield[name=widget_d_1]'),
        wd2 = cmp.down('displayfield[name=widget_d_2]'),
        wd3 = cmp.down('displayfield[name=widget_d_3]');//cmp.down('lable[name=name]').setText('weew');
        
        this.task = {               
                run: function(){
                    chart1.store.load();
                    chart2.store.load();
                    chart3.store.load();
                    Ext.Ajax.request({
                    url: just.getUrl('/sys/user/orderstatis_q_r.action'),                   
                    success : function(response, options){
                        var json = Ext.JSON.decode(response.responseText);
                        if (json.status == '200') {
                           wd1.setValue('订单总数<br>'+json.rows[0].total+'（单）');
                           wd2.setValue('成功订单总数<br>'+json.rows[0].oknum+'（单）');
                           wd3.setValue('成功订票总张数<br>'+json.rows[0].ticknum+'（张）');
                            
                        }
                        
                   }
                });
            
                  },
                  interval: 1200000 
        };
        //Ext.TaskManager.start(this.task);
        //console.log( cmp.down('displayfield[name=widget_d_1]').setValue('sd'))

    },

    onAxisRangeChange: function (axis, range) {
        // this.lookupReference('chart') will fail here,
        // as at the time of this call
        // the chart is not yet in the component tree,
        // so we have to use axis.getChart() instead.
        var chart = axis.getChart(),
            store = chart.getStore(),
            sum = 0,
            mean;

        store.each(function (rec) {
            sum += rec.get('highF');
        });

        mean = sum / store.getCount();

        axis.setLimits({
            value: mean,
            line: {
                title: {
                    text: 'Average high: ' + mean.toFixed(2) + '°F'
                },
                lineDash: [2,2]
            }
        });
    },

    itemAnimationDuration: 0,

    // Disable item's animaton for editing.
    onBeginItemEdit: function (chart, interaction, item) {
        var itemsMarker = item.sprite.getMarker(item.category),
            fx = itemsMarker.getTemplate().fx; // animation modifier

        this.itemAnimationDuration = fx.getDuration();
        fx.setDuration(0);
    },

    // Restore item's animation when editing is done.
    onEndItemEdit: function (chart, interaction, item, target) {
        var itemsMarker = item.sprite.getMarker(item.category),
            fx = itemsMarker.getTemplate().fx;

        fx.setDuration(this.itemAnimationDuration);
    },
    onRefreshToggle: function(tool, e, owner) {
        var me = this,
            store=this.getViewModel().getStore('dashboardfulllinechartstore'),
            items=Ext.Array.from(store && store.getData().items),
            num_items=items.length;

        if (tool.toggleValue){
            me.clearChartUpdates(owner);
        } else {
            if (num_items) {
                me.chartTaskRunner  = me.chartTaskRunner || Ext.create('Ext.util.TaskRunner');
                me.chartTaskRunner.start({
                    run : function () {
                        this.last_x += this.last_x - this.second_last_x;
                        var first = this.items[0].data;
                        this.store.removeAt(0);
                        this.store.add({xvalue: first.xvalue, y1value: first.y1value, y2value: first.y2value});
                        this.count++;
                    },
                    store : store,
                    count : 0,
                    items : items,
                    last_x : items[num_items -1].data.xvalue,
                    second_last_x : items[num_items -2].data.xvalue,
                    interval : 200
                });
            }
        }

        // change the toggle value
        tool.toggleValue = !tool.toggleValue;
    },

    clearChartUpdates : function() {
        this.chartTaskRunner = Ext.destroy(this.chartTaskRunner);
    },
    
    onDestroy: function () {
        this.clearChartUpdates();
        this.callParent();
    },

    
    onHideView: function () {
        this.clearChartUpdates();
    },
    onSeriesTooltipRender: function (tooltip, record, item) {
        tooltip.setHtml(record.get('type') + ': ' + record.get('num') + '');
    }


});