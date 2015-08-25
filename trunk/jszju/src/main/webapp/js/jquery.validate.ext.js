jQuery.validator.addMethod(
        "datelessthan",
        function(value, element, params)
        {
            //起始时间必须小于结束时间
            return this.optional(element) || value == null || jQuery(params).val() == null || Date.parse(value) < Date.parse(jQuery(params).val());
        },
        "Please specify the correct domain for your documents");

jQuery.validator.addMethod(
        "floatNumber",
        function(value, element, params)
        {
            //判断是否为长度为params[0]，精度为params[1]的数值
            var r = new RegExp("^[-+]?(\\d{1," + (params[0] - params[1]) + "}(\\.\\d{0," + params[1] + "})?)$");
            return this.optional(element) || r.test(value);
        },
        "Please specify the correct domain for your documents");
        
jQuery.validator.addMethod(
        "Integer",
        function(value, element, params)
        {
            //判断是否为整数
            var r = new RegExp("^[-+]?(\\d+$)");
            return this.optional(element) || r.test(value);
        },
        "Please specify the correct domain for your documents");

var errorDivObj;

jQuery().ready(
        function()
        {

            jQuery("form").prepend("<div id='errorDiv' class='error'></div>");

            errorDivObj = jQuery("#errorDiv");


        });