export interface Education {
  id?: number;
  degree: string;
  institution: string;
  fieldOfStudy: string;
  startDate: Date;
  endDate?: Date;
  description?: string;
}
