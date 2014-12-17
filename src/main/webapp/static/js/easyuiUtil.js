//表单验证输入字符必须是数字
$.extend($.fn.validatebox.defaults.rules, {
    numberRegex: {
        validator: function(value, param){
        	var r = /\d+$/;
            return (r.test(value));
        },
        message: '请输入数字'
    }
});

//将表单序列化JSON对象
$.fn.serializeObject = function() {
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};