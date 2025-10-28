import { Certification } from "./Certification";
import { Education } from "./Education";
import { Experience } from "./Experience";
import { LanguageSkill } from "./LanguageSkill";
import { Skill } from "./Skill";

export interface Curriculum {
  id?: number;
  country: string;
  city: string;
  phone?: string;
  title: string;
  summary?: string;
  theme: string;
  language: string;
  experiences: Experience[];
  educations: Education[];
  skills: Skill[];
  certifications: Certification[];
  languageSkills: LanguageSkill[];
}
