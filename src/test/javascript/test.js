should = require('chai').should()
require('../../../app/lunar.js');

describe('LunarMission Interface', function () {

    beforeEach(function() {
        this.canvas = { height: 500 };
    });

    it('should convert altitude to canvas coordinate', function () {
        var altitude = 50;
        toCanvasCoordinate(altitude, this.canvas).should.equal(250);
    });

    it('should increase canvas y when altitude decreases', function () {
        var altitude = 25;
        toCanvasCoordinate(altitude, this.canvas).should.equal(375);
    });

    it('should take into account canvas height', function () {
        var altitude = 50;
        this.canvas.height = 400;
        toCanvasCoordinate(altitude, this.canvas).should.equal(200);
    });

});



