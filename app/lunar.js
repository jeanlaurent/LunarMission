toCanvasCoordinate = function (altitude, canvas) {
    return canvas.height - altitude * (canvas.height / 100);
}
