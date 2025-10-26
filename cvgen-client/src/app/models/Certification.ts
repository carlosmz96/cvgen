export interface Certification {
  id?: number;
  name: string;
  issuer: string;
  obtainedDate: Date;
  validUntil?: Date;
  credentialUrl?: string;
}
