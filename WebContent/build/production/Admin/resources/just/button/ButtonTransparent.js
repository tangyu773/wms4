/**
 * Created by zouhao on 2015-12-23 10:00:09.
 * 自定义一个button
 */
Ext.define('Ext.just.button.ButtonTransparent', {
	extend : 'Ext.button.Button',
	alias : 'widget.buttontransparent',
	// 类初始化时执行
	initComponent : function() {
		// 设置事件监听
		this.listeners = {
			// 鼠标移开按钮
			mouseout : function() {
				this.setTransparent(document.getElementById(this.id));
			},
			// 鼠标放置在按钮上
			mouseover : function() {
				this.setOnClick(document.getElementById(this.id));
			},
			// 正常情况
			afterrender : function() {
				this.setTransparent(document.getElementById(this.id));
			},
			//点击时间
			click : function() {
				this.setOnClick(document.getElementById(this.id));
			}
		};
		this.callParent(arguments);
	},
	/**
	 * 点击事件
	 */
    setOnClick : function(b) {
		b.style.backgroundImage = 'inherit';
		b.style.backgroundColor = '#696969';
		b.style.borderColor = '#696969';
		b.style.borderRadius = '5px';
    },
    /**
     * 其他时间
     */
	setTransparent : function(b) {
		b.style.backgroundImage = 'inherit';
		b.style.backgroundColor = 'inherit';
		b.style.borderColor = 'transparent';
	}
});