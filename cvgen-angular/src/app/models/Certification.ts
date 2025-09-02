import { Curriculum } from "./Curriculum";

export interface Certification {
  id: number;
  curriculum: Curriculum;
  name: string;
  issuer: string;
  dateObtained: Date;
  validUntil: Date;
  credentialId: string;
  credentialUrl: string;
}
