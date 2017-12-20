import { BaseEntity } from './../../shared';

export class Disciplina implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public cargaHoraria?: number,
        public sigla?: string,
        public ementa?: string,
        public creditos?: number,
        public atividades?: BaseEntity[],
    ) {
    }
}
