"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ResultType = void 0;
class ResultType {
    /**
     * Getter $result
     * @return {string}
     */
    get $result() {
        return this.result;
    }
    /**
     * Setter $result
     * @param {string} value
     */
    set $result(value) {
        this.result = value;
    }
    /**
     * Getter $type
     * @return {string}
     */
    get $type() {
        return this.type;
    }
    /**
     * Setter $type
     * @param {string} value
     */
    set $type(value) {
        this.type = value;
    }
    /**
     * Getter $attributes
     * @return {T}
     */
    get $attributes() {
        return this.attributes;
    }
    /**
     * Setter $attributes
     * @param {T} value
     */
    set $attributes(value) {
        this.attributes = value;
    }
    constructor(result, type, attributes) {
        this.$attributes = attributes;
        this.$result = result;
        this.$type = type;
    }
}
exports.ResultType = ResultType;
