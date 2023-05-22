import ProjectList from "./ProjectList";
import useFetch from "../../hook/useFetch";

const Projects = () => {
  const { data, error, isPending } = useFetch("/api/v1/project");
  if (isPending) return <h1>Loading</h1>;
  return <ProjectList projects={data} />;
};

export default Projects;
