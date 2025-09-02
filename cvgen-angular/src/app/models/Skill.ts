import { Curriculum } from "./Curriculum";

export interface Skill {
  id: number;
  curriculum: Curriculum;
  name: string;
  level: string;
  category: string;
}
