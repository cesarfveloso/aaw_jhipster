import { BaseEntity } from './../../shared';

export class Professor implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public tituloFormacao?: string,
        public areaConhecimento?: string,
        public disciplinas?: BaseEntity[],
        public professors?: BaseEntity[],
        public usuario?: BaseEntity,
    ) {
    }
}
