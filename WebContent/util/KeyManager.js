Ext.define(just.createUtil('KeyManager'), {
	alternateClassName: "GKeyManager",
	singleton: true,

	maskBackspace: function() {	
		var DOC = Ext.getDoc();
		DOC.on({
			'keydown': maskBS,
			'keyup': maskBS
		});

		function maskBS(event, targetHtml) {
			var keycode = event.getKey();
			var obj = targetHtml;
//			console.log(keycode);
			if (keycode == Ext.EventObject.BACKSPACE) {
				if (obj != null && obj.tagName != null && (obj.tagName.toLowerCase() == "input" || obj.tagName.toLowerCase() == "textarea")) {
					//readOnly  
					var fieldEl, fieldCmp;
					fieldEl = Ext.get(obj).up('table.x-form-item');
					if (fieldEl && fieldEl.id) {
						fieldCmp = Ext.getCmp(fieldEl.id);
					}
					if (fieldCmp && (fieldCmp.readOnly || fieldCmp.editable === false)) {
						event.stopEvent();
					}
				} else {
					event.stopEvent();
				}
			}/*else if(keycode == Ext.EventObject.TAB)
			{
				var fieldEl, fieldCmp;
					fieldEl = Ext.get(obj).up('table.x-form-item');
					if (fieldEl==undefined ) {
						event.stopEvent();
					}
					
			}*/
		}
	}
});