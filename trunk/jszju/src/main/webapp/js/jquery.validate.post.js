jQuery(document).ready(
function(){
var validator=jQuery("form").validate();
if(validator!=null){
	
	validator.settings.onclick = false;
	validator.settings.onfocusout = false;
	validator.settings.onkeyup = false;
	validator.settings.errorLabelContainer=errorDivObj;
	validator.settings.wrapper="li";
	validator.labelContainer = jQuery(validator.settings.errorLabelContainer);
	validator.errorContext = validator.labelContainer.length && validator.labelContainer || jQuery(validator.currentForm);
	validator.containers = jQuery(validator.settings.errorContainer).add( validator.settings.errorLabelContainer );
	validator.showErrors=function(){
		if(this.numberOfInvalids()>0){
		
		this.defaultShowErrors();
		errorDivObj.css("display","none");
		ymPrompt.errorInfo({title:"错误",message:errorDivObj.html()});
		}
		
	};
}

}
);