package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("piersto", "java_1"))
                .commits();
        for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
            // Print out messages of the commits
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
