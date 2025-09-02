import { Curriculum } from "./Curriculum";

export interface Education {
  id: number;
  curriculum: Curriculum;
  degree: string;
  educationField: string;
  institution: string;
  location: string;
  startDate: Date;
  endDate: Date;
  description: string;
}
