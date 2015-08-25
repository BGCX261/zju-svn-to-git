function SetHeight(iframeId, minHeight)
{
    var iframe = document.getElementById(iframeId);
    if (iframe && !window.opera)
    {
        if (iframe.contentDocument && iframe.contentDocument.body.offsetHeight)
        {
            iframe.height = iframe.contentDocument.body.offsetHeight;
        }
        else if (iframe.Document && iframe.Document.body.scrollHeight)
        {
            iframe.height = iframe.Document.body.scrollHeight;
        }

        if (minHeight > 0 && iframe.height < minHeight)
        {
            iframe.height = minHeight;
        }
    }
}

function SetHeightEX(iframeId, minHeight, totalMS)
{
    var delayMS = 100;
    SetHeight(iframeId, minHeight);
    totalMS = totalMS - delayMS;
    if (totalMS > 0)
    {
        window.setTimeout("SetHeightEX('" + iframeId + "', '" + minHeight + "', '" + totalMS + "')", delayMS);
    }
}
