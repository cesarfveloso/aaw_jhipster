import { BaseEntity } from './../../shared';

export class Atividade implements BaseEntity {
    constructor(
        public id?: number,
        public valor?: number,
        public tipo?: string,
        public dataFinal?: string,
        public notas?: BaseEntity[],
        public entregas?: BaseEntity[],
        public disciplina?: BaseEntity,
    ) {
    }
}
