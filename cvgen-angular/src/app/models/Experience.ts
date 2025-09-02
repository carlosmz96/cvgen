import { Curriculum } from "./Curriculum";

export interface Experience {
  id: number;
  curriculum: Curriculum;
  position: string;
  company: string;
  location: string;
  startDate: Date;
  endDate: Date;
  description: string;
  highlights: string[];
}
