function _prev(actionType)
{
    var target = parseInt(document.getElementById("currentPageNo").value) - 1;
    _doOp(target, actionType);
}
function _next(actionType)
{
    var target = parseInt(document.getElementById("currentPageNo").value) + 1;
    _doOp(target, actionType);
}

function _goto(actionType)
{
    var target = parseInt(document.getElementById("pageNo").value);
    _doOp(target, actionType);
}

function _doOp(target, actionType)
{
    var total = parseInt(document.getElementById("totalPages").value);
    document.getElementById("pageNo").value = target? ((target > total ? total : target) < 1 ? 1 : target):1;
    document.forms[0].actionType.value = actionType.trim() == '' ? "select" : actionType;
    document.forms[0].submit();
}

