export class ResultType<T>{
    private result!: string;
    private type!: string;
    private attributes!: T;

    /**
     * Getter $result
     * @return {string}
     */
	public get $result(): string {
		return this.result;
	}

    /**
     * Setter $result
     * @param {string} value
     */
	public set $result(value: string) {
		this.result = value;
	}

    /**
     * Getter $type
     * @return {string}
     */
	public get $type(): string {
		return this.type;
	}

    /**
     * Setter $type
     * @param {string} value
     */
	public set $type(value: string) {
		this.type = value;
	}

    /**
     * Getter $attributes
     * @return {T}
     */
	public get $attributes(): T {
		return this.attributes;
	}

    /**
     * Setter $attributes
     * @param {T} value
     */
	public set $attributes(value: T) {
		this.attributes = value;
	}

	constructor(result: string, type: string, attributes : T) {
        this.$attributes = attributes;
        this.$result = result;
        this.$type = type;
	}

}