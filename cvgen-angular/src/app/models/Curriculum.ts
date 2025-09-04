import { Certification } from "./Certification";
import { Education } from "./Education";
import { Experience } from "./Experience";
import { Skill } from "./Skill";

export interface Curriculum {
  id: number;
  fullName: string;
  title: string;
  email: string;
  locationCity: string;
  locationCountry: string;
  summary: string;
  linkedinUrl: string;
  githubUrl: string;
  portfolioUrl: string;
  createdAt: Date;
  updated: Date;
  experiences: Experience[];
  skills: Skill[];
  educations: Education[];
  certifications: Certification[];
}
